package otus.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import otus.domain.Book;
import otus.service.LibraryService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = BookController.class)
public class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LibraryService libraryService;


	@Test
	public void booksTest() throws Exception {
		List<Book> books = ImmutableList.of(new Book("name", ImmutableSet.of(), ImmutableSet.of()));

		when(libraryService.getAllBooks())
				.thenReturn(books);

		ResultActions resultActions = mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("list"))
				.andExpect(model().attribute("books", equalTo(books)));
	}

	//TODO:: Добавить остальные тесты

}
