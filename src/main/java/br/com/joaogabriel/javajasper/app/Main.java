package br.com.joaogabriel.javajasper.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

import br.com.joaogabriel.javajasper.jdbc.factory.ConnectionFactory;
import br.com.joaogabriel.javajasper.service.JasperReportService;

public class Main {

	public static void main(String[] args) throws SQLException{
		//openJrxml("06");
		exportPdf("01", "C:\\Users\\biel0\\Downloads\\"+"jasper-"+UUID.randomUUID()+"-"+LocalDate.now()+".pdf");
	}

	private static void exportPdf(String fileNumber, String path) throws SQLException {
		Connection connection = ConnectionFactory.getInstance().getConnection();
		JasperReportService jasperService = new JasperReportService();
		String reportName = String.format("reports/jrxml/funcionarios-%s.jrxml", fileNumber);
		jasperService.exportToPdf(reportName, connection, path);
		connection.close();
		
	}

	private static void openJrxml(String fileNumber) throws SQLException {
		Connection connection = ConnectionFactory.getInstance().getConnection();
		JasperReportService jasperService = new JasperReportService();
		jasperService.addParameter("SEARCH_BY_NAME", "%Duarte%");
		String reportName = String.format("reports/jrxml/funcionarios-%s.jrxml", fileNumber);
		jasperService.openJasperViewer(reportName, connection);
		connection.close();
		
	}
}
