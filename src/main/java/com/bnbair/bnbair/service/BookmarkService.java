// package com.bnbair.bnbair.service;

// import java.util.List;

// import org.springframework.stereotype.Service;

// import com.bnbair.bnbair.domain.Bookmark;
// import com.bnbair.bnbair.domain.BookmarkId;
// import com.bnbair.bnbair.domain.Property;
// import com.bnbair.bnbair.domain.User;
// import com.bnbair.bnbair.repository.BookmarkRepository;

// @Service
// public class BookmarkService {
// private final BookmarkRepository bookmarkRepository;

// public BookmarkService(BookmarkRepository bookmarkRepository) {
// this.bookmarkRepository = bookmarkRepository;
// }

// public List<Bookmark> getAllBookmarks() {
// return (List<Bookmark>) bookmarkRepository.findAll();
// }

// public Bookmark createBookmark(Bookmark bookmark) {
// return bookmarkRepository.save(bookmark);
// }

// public void deleteBookmark(User user, Property property) {
// Bookmark bookmark = bookmarkRepository.findById(new BookmarkId(user,
// property))
// .orElseThrow(() -> new RuntimeException(
// "Bookmark not found with user id: " + user.getId() + " and property id: " +
// property.getId()));

// bookmarkRepository.delete(bookmark);
// }
// }
