public class Book {

    private int b00k_id;
    private String book_name;
    private int price;
    private int publisher_id;

    public Book() {
    }

    public Book(int b00k_id, String book_name, int price, int publisher_id) {
        this.b00k_id = b00k_id;
        this.book_name = book_name;
        this.price = price;
        this.publisher_id = publisher_id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "b00k_id=" + b00k_id +
                ", book_name='" + book_name + '\'' +
                ", price=" + price +
                ", publisher_id=" + publisher_id +
                '}';
    }
}
