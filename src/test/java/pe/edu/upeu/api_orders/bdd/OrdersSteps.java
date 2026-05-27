package pe.edu.upeu.api_orders.bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pe.edu.upeu.api_orders.model.Order;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrdersSteps {

    private ResponseEntity<Order> response;
    private RestTemplate restTemplate = new RestTemplate();
    private String baseUrl = "http://localhost:8080/api/orders";

    @Given("the orders API is up")
    public void the_orders_api_is_up() {
        // Método de verificación vacío para pasar la condición inicial
    }

    @When("I send a POST request to {string} with name {string} and price {double}")
    public void i_send_a_post_request(String path, String name, Double price) {
        Order order = new Order();
        order.setCustomer(name); // Asegúrate de que el campo de tu entidad se llame así
        order.setAmount(price);
        
        response = restTemplate.postForEntity("http://localhost:8080" + path, order, Order.class);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer statusCode) {
        assertEquals(statusCode, response.getStatusCode().value());
    }
}