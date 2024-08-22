package br.com.joaogabriel.javajasper.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class JasperReportService {

	private Map<String, Object> parameters = new HashMap<String, Object>();
	private Logger logger = Logger.getLogger(getClass().getSimpleName());
	
	public void addParameter(String key, Object value) {
		this.parameters.put(key, value);
	}
	
	private JasperReport jrxmlCompile(String file) {
		InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(file);
		try {
			return JasperCompileManager.compileReport(resourceAsStream);
		} catch (JRException e) {
			logger.log(Level.SEVERE, "Fail to compile report. File: ", file);
			throw new RuntimeException();
		}
	}
	
	//To desktop application
	public void openJasperViewer(String jrxml, Connection connection) {
		JasperReport report = jrxmlCompile(jrxml);
		try {
			JasperPrint print = JasperFillManager.fillReport(report, this.parameters, connection);
			JasperViewer viewer = new JasperViewer(print);
			viewer.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	
	//To server application
	public void exportToPdf(String jrxml, Connection connection, String pathToSaved) {
		JasperReport report = jrxmlCompile(jrxml);
		try {
			JasperPrint print = JasperFillManager.fillReport(report, this.parameters, connection);
			OutputStream outputStream = new FileOutputStream(pathToSaved);
			JasperExportManager.exportReportToPdfStream(print, outputStream);
		} catch (JRException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
