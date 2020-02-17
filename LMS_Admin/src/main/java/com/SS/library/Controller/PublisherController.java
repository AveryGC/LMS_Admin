/**
 * 
 */
package com.SS.library.Controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.SS.library.Entity.Book;
import com.SS.library.Entity.Publisher;
import com.SS.library.Service.PublisherService;

/**
 * @author acorb
 *
 */
@RestController
public class PublisherController {

	@Autowired
	PublisherService publisherService;
	
	@RequestMapping(path="**/Admin/publishers", method = RequestMethod.GET)
	public ResponseEntity<List<Publisher>> getPublishers(){
		try {
			List<Publisher> publishers = publisherService.readAllPublisher();
			return new ResponseEntity<List<Publisher>>(publishers,HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<List<Publisher>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(path="**/Admin/publishers", method = {RequestMethod.PUT,RequestMethod.DELETE})
	public ResponseEntity<String> methodNotAllowed(){
		return new ResponseEntity<String>(HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@RequestMapping(path= {"**/Admin/publishers/{publisherId}","**/Admin/publishers"}, method = RequestMethod.POST)
	public ResponseEntity<Publisher> addPublisher(@RequestBody Publisher publisher){
		try {
			publisherService.addPublisher(publisher);
			return new ResponseEntity<Publisher>(publisher,HttpStatus.ACCEPTED);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Publisher> (HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(path="**/Admin/publishers/{publisherId}", method = RequestMethod.PUT)
	public ResponseEntity<Publisher> updatePublisher(@RequestBody Publisher publisher){
		try {
			publisherService.updatePublisher(publisher);
			return new ResponseEntity<Publisher>(publisher,HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Publisher> (HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(path="**/Admin/publishers/{publisherId}", method = RequestMethod.GET)
	public ResponseEntity<Publisher> readPublisher(@PathVariable int publisherId){
		try {
			Publisher publisher = publisherService.readPublisherById(publisherId);
			return new ResponseEntity<Publisher>(publisher,HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Publisher> (HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(path="**/Admin/publishers/{publisherId}", method = RequestMethod.DELETE)
	public ResponseEntity<Publisher> deletePublisher(@RequestBody Publisher publisher){
		try {
			publisherService.deletePublisher(publisher);
			return new ResponseEntity<Publisher>(publisher,HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Publisher> (HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(path="**/Admin/publishers/{publisherId}/books")
	public ResponseEntity<List<Book>> readBooksByPublisher(@PathVariable int publisherId){
		try {
			List<Book> books = publisherService.readBookByPublisher(publisherId);
			return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
		}
	}
}
