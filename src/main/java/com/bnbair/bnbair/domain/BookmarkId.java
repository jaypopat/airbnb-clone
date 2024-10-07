// package com.bnbair.bnbair.domain;

// import jakarta.persistence.Embeddable;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.ManyToOne;
// import lombok.EqualsAndHashCode;
// import lombok.Getter;
// import lombok.Setter;

// import java.io.Serializable;

// @Getter
// @Setter
// @EqualsAndHashCode
// @Embeddable
// public class BookmarkId implements Serializable {

// @ManyToOne(fetch = FetchType.EAGER)
// private User user;

// @ManyToOne(fetch = FetchType.EAGER)
// private Property property;

// public BookmarkId() {
// }

// public BookmarkId(User user, Property property) {
// this.user = user;
// this.property = property;
// }

// @Override
// public String toString() {
// return "BookmarkId [user=" + user + ", property=" + property + "]";
// }
// }
