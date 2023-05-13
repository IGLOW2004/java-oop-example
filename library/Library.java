import java.util.ArrayList;

class Library {
  public ArrayList<Book> books = new ArrayList<Book>();
  public ArrayList<Member> members = new ArrayList<Member>();

  public void addMember(Member member) {
    if (!isMemberIdExist(member.id)) {
      this.members.add(member);
    } else {
      System.out.println("Data Sudah Ada");
    }
  }

  public Boolean isMemberIdExist(String id) {
    Boolean isExist = false;
    for (Member member : this.members) {
      if (member.id.equals(id)) {
        isExist = true;
      }
    }
    return isExist;
  }

  // menambahkan Buku
  public void addBook(Book book) {
    if (!isBookIdExist(book.id)) {
      this.books.add(book);
    } else {
      System.out.println("Data Sudah Ada");
    }
  }

  public Boolean isBookIdExist(String id) {
    Boolean isExist = false;
    for (Book book : this.books) {
      if (book.id.equals(id)) {
        isExist = true;
      }
    }
    return isExist;
  }

  public void giveBook(String bookId, String memberId) {
    try {
      Book book = this.getBookById(bookId);

      Member member = this.getMemberById(memberId);
      int memberIndex = this.getMemberIndex(member);
      this.books.remove(book);
      this.members.get(memberIndex).borrowedBooks.add(book);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void receiveBook(String bookId, String memberId) {
    try {
      Member member = this.getMemberById(memberId);
      int memberIndex = this.getMemberIndex(member);

      Book book = this.members.get(memberIndex).getBookById(bookId);

      this.books.add(book);
      this.members.get(memberIndex).borrowedBooks.remove(book);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private int getMemberIndex(Member member) {
    return this.members.indexOf(member);
  }

  private Member getMemberById(String id) throws Exception {
    for (Member member : this.members) {
      if (member.id.equals(id)) {
        return member;
      }
    }
    throw new Exception("Member dengan id " + id + " tidak ditemukan");
  }

  private Book getBookById(String id) throws Exception {
    for (Book book : this.books) {
      if (book.id.equals(id)) {
        return book;
      }
    }
    throw new Exception("Buku tidak ditemukan");
  }
}