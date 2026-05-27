package pe.edu.upeu.api_orders.bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pe.edu.upeu.api_orders.model.Order;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrdersSteps {

    @LocalServerPort
    private int port; // Inyecta dinámicamente el puerto aleatorio en el que levanta Tomcat

    private ResponseEntity<Order> response;
    private RestTemplate restTemplate = new RestTemplate();

    @Given("the orders API is up")
    public void the_orders_api_is_up() {
        // Método de verificación vacío para pasar la condición inicial
    }

    @When("I send a POST request to {string} with name {string} and price {double}")
    public void i_send_a_post_request(String path, String name, Double price) {
        Order order = new Order();
        order.setCustomer(name); 
        order.setAmount(price);
        
        // Se construye la URL usando el puerto dinámico (ej: http://localhost:36001/api/orders)
        String url = "http://localhost:" + port + path;
        
        response = restTemplate.postForEntity(url, order, Order.class);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer statusCode) {
        assertEquals(statusCode, response.getStatusCode().value());
    }
}