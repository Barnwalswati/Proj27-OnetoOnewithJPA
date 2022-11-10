package com.hibernate.test.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hibernate.DTO.Employee;
import com.hibernate.test.entity.Book;

import com.hibernate.test.service.BookService;

import okhttp3.MediaType;


@RestController
@RequestMapping(value = "/book")
public class BookController {
	
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Autowired
    private BookService bookService;
    
    @Autowired
    private RestTemplate restTemplate;
    
    private final String baseUrl = "http://localhost:8081/api/employees";

    @RequestMapping(value = "/savebook", method = RequestMethod.POST)
    @ResponseBody
    public Book saveBook(@RequestBody Book book) {
        Book bookResponse = bookService.saveBook(book);
        return bookResponse;
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
    @ResponseBody
    public Book getBookDetails(@PathVariable int bookId) {
        Book bookResponse = bookService.findByBookId(bookId);

        return bookResponse;
    }

    //RestTemplate GET request
    @GetMapping("/fetchEmployee")
	public List<Employee> getlistofEmplyees() throws IOException {
		ResponseEntity<Employee[]> res = restTemplate.getForEntity(baseUrl, Employee[].class);
		return Arrays.asList(res.getBody());
	}
    
    //okHttp POST request
    @PostMapping("/addEmployee")
    public HttpStatus addEmployee(@RequestBody Employee employee) throws IOException {
    	ResponseEntity<HttpStatus> res = restTemplate.postForEntity(baseUrl, employee, HttpStatus.class );
		return res.getBody();
    }
    
    //okHttp DELETE request
    @DeleteMapping("/deleteEmployee/{id}")
    public void deleteEmployee(@PathVariable int id) throws IOException {
    	restTemplate.delete(baseUrl+id);
    }
    
    //okHttp PUT request
    @PutMapping("/updateEmployee")
    public void updateEmployee(@RequestBody Employee employee) throws IOException {
    	restTemplate.put(baseUrl, employee);
    }
}