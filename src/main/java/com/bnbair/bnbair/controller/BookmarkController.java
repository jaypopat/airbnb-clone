// package com.bnbair.bnbair.controller;

// import java.util.List;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.bnbair.bnbair.domain.Bookmark;
// import com.bnbair.bnbair.service.BookmarkService;

// @RestController
// @RequestMapping("bookmarks")
// public class BookmarkController {

// private final BookmarkService bookmarkService;

// public BookmarkController(BookmarkService bookmarkService) {
// this.bookmarkService = bookmarkService;
// }

// @GetMapping
// public ResponseEntity<List<Bookmark>> getAllBookmarks() {
// List<Bookmark> bookmarks = bookmarkService.getAllBookmarks();
// return ResponseEntity.ok(bookmarks);
// }

// @GetMapping("/{id}")
// public ResponseEntity<Bookmark> getBookmarkById(@PathVariable Long id) {
// Bookmark bookmark = bookmarkService.getBookmarkById(id);
// return ResponseEntity.ok(bookmark);
// }

// @PostMapping
// public ResponseEntity<Bookmark> createBookmark(@RequestBody Bookmark
// bookmark) {
// Bookmark createdBookmark = bookmarkService.createBookmark(bookmark);
// return ResponseEntity.ok(createdBookmark);
// }

// @DeleteMapping("/{id}")
// public ResponseEntity<Void> deleteBookmark(@PathVariable Long id) {
// bookmarkService.deleteBookmark(id);
// return ResponseEntity.noContent().build();
// }
// }
