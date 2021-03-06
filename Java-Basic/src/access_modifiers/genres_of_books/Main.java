package access_modifiers.genres_of_books;

import access_modifiers.genres_of_books.childGenres.*;
import access_modifiers.genres_of_books.entity.Book;
import access_modifiers.genres_of_books.entity.Genre;
import access_modifiers.genres_of_books.service.BookServiceImpl;

public class Main {
    public static void main(String[] args) {
        //Создадим первую книгу с тремя жанрами
        Book book1 = new Book("Властелин колец", new Genre[]{new StoryGenre(), new ProseGenre(), new FantasticGenre()});
        //Создадим вторую книгу с двумя жанрами
        Book book2 = new Book("Шерлок Холмс", new Genre[] {new NovelGenre(), new DetectiveGenre()});

        //Создадим объект `BookService` - для фильтрации
        BookServiceImpl bookService = new BookServiceImpl();

        //Вызовем метод фильтрации, куда передадим список книг и жанр фильтрации в качестве аргументов
        bookService.filterBookByGenre(new Book[]{book1, book2}, new StoryGenre());
        bookService.filterBookByGenre(new Book[]{book1, book2}, new DetectiveGenre());
        bookService.filterBookByGenre(new Book[]{book1, book2}, new NarrativeGenre());
        bookService.filterBookByGenre(new Book[]{book1, book2}, new VerseGenre());
    }
}
