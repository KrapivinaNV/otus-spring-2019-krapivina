package otus.controller;

import com.google.common.collect.Sets;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import otus.service.LibraryService;

@Controller
public class BookController {

	private final LibraryService libraryService;

	@Autowired
	public BookController(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	@GetMapping("/")
	public String getBookListView(Model model) {
		model.addAttribute("books", libraryService.getAllBooks());
		return "list";
	}

	@GetMapping("/add")
	public String getAddView(Model model) {
		return "add";
	}

	@PostMapping("/new")
	public String saveNewBook(@RequestParam("name") String name, Model model) {
		libraryService.addBook(name, Sets.newHashSet(), Sets.newHashSet());
		model.addAttribute("books", libraryService.getAllBooks());
		return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String getEditView(@PathVariable("id") UUID id, Model model) {
		model.addAttribute("book", libraryService.getBook(id).orElseThrow(IllegalArgumentException::new));
		return "edit";
	}

	@PostMapping("/update")
	public String updateBook(@RequestParam("id") UUID id, @RequestParam("name") String name, Model model) {
		libraryService.updateBookName(name, id);
		model.addAttribute("books", libraryService.getAllBooks());
		return "redirect:/";
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") UUID id, Model model) {
		libraryService.deleteBook(id);
		model.addAttribute("books", libraryService.getAllBooks());
		return "redirect:/";
	}

	@GetMapping("/comments/{id}")
	public String getCommentsView(@PathVariable("id") UUID id, Model model) {
		model.addAttribute("comments", libraryService.getAllCommentByBookId(id));
		model.addAttribute("bookName", libraryService.getBook(id).orElseThrow(IllegalArgumentException::new).getName());
		return "comments";
	}

}
