package br.com.joaogabriel.javajasper.jdbc.factory;

import java.sql.Connection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConnectionFactoryTest {

	@Test
	@DisplayName("Given a successfully connection, then must return an instance of object connection")
	public void given_connection_then_ReturnConnectionObject() {
		Connection connection = ConnectionFactory.getInstance().getConnection();
		Assertions.assertNotNull(connection);
	}
}
