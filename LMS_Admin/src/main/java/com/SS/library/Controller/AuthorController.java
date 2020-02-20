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

import com.SS.library.Entity.Author;
import com.SS.library.Entity.Book;
import com.SS.library.Service.AuthorService;

/**
 * @author acorb
 *
 */
@RestController
public class AuthorController {
	
	@Autowired
	AuthorService authorService;
	
	@RequestMapping(path = "/admin/authors", method = RequestMethod.GET)
	public ResponseEntity<List<Author>> getAuthors(){
		try {
			List <Author> authors = authorService.readAllAuthors();
			return new ResponseEntity<List<Author>>(authors,HttpStatus.OK);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<List<Author>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(path = "/admin/authors", method = {RequestMethod.DELETE, RequestMethod.PUT})
	public ResponseEntity<String> authorMethodNotAllowed() {
		return new ResponseEntity<String>(HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@RequestMapping(path = "/admin/authors/{authorId}", method = RequestMethod.GET)
	public ResponseEntity<Author> getAuthor(@PathVariable int authorId){
		try {
			Author author = authorService.readAuthorAtId(authorId);
			return new ResponseEntity<Author>(author,HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Author>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(path = {"/admin/authors"} , method = RequestMethod.POST)
	public ResponseEntity<Author> addAuthor(@RequestBody Author author){
		try {
			authorService.addAuthor(author);
			return new ResponseEntity<Author>(author,HttpStatus.ACCEPTED);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Author>(HttpStatus.CONFLICT);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Author>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(path = "/admin/authors/{authorId}", method = RequestMethod.PUT)
	public ResponseEntity<Author> updateAuthor(@RequestBody Author author){
		try {
			authorService.updateAuthor(author);
			return new ResponseEntity<Author>(author,HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Author>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(path = "/admin/authors/{authorId}", method = RequestMethod.DELETE)
	public ResponseEntity<Author> deleteAuthor(@PathVariable int authorId){
		try {
			authorService.deleteAuthor(authorId);
			return new ResponseEntity<Author>(HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Author>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(path="/admin/authors/{authorId}/books", method = RequestMethod.GET)
	public ResponseEntity<List<Book>>readBooksByAuthor(@PathVariable int authorId){
		try {
			List <Book> books = authorService.readBookAuthorByAuthor(authorId);
			return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
		}catch(SQLException e) {
			return new ResponseEntity<List<Book>>(HttpStatus.NOT_FOUND);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<List<Book>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(path="/admin/authors/{authorId}/books/{bookId}", method = {RequestMethod.POST,RequestMethod.PUT})
	public ResponseEntity<Object>addToBookAuthor(@PathVariable int authorId, @PathVariable int bookId){
		try {
			authorService.addBookToAuthor(authorId, bookId);
			return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
		}catch(SQLException e) {
			return new ResponseEntity<Object>(HttpStatus.CONFLICT);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(path="/admin/authors/{authorId}/books/{bookId}", method = {RequestMethod.DELETE})
	public ResponseEntity<Object>deleteBookAuthor(@PathVariable int authorId, @PathVariable int bookId){
		try {
			authorService.deleteBookFromAuthor(authorId, bookId);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}catch(SQLException e) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
