package web.mvc.dao;
import web.mvc.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {                                                   
    private String driver = "com.mysql.jdbc.Driver";
    private String uri = "jdbc:mysql://localhost:3306/javawebmvc?useUnicode=true&characterEncoding=UTF-8";
    private String user = "root";
    private String pass = "";
    private Connection connect = null;
    // เชื่อมต่อฐานข้อมูล
    public ProductDao() {
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(uri,user,pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    // ปิดการเชื่อมต่อฐานข้อมูล
    public void close() {
        try {
            if(connect != null) {
                connect.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ดึงข้อมูล Product ขึ้นมา
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<Product>();
        String sql = "SELECT * FROM product ORDER BY id";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while(result.next()) {
                Product p = new Product();
                p.setId(result.getInt("id"));
                p.setName(result.getString("name"));
                p.setPrice(result.getInt("price"));
                productList.add(p);
            }
           connect.close();  
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
    // เพิ่มข้อมูลลงตาราง Producr
    public boolean add(Product product) {
        boolean result = false;
        String sql = "INSERT INTO product"
                   + " (name, price)"
                   + " VALUES(?, ?)";
        
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            int record = ps.executeUpdate();
            if(record >= 1 ) {
                result = true;
            } else {
                result = false;
            }
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    // แก้ไขข้อมูลในตาราง Product
    public boolean edit(Product product) {
        boolean result = false;
        String sql = "UPDATE product SET name = ?, price = ?"
                   + " WHERE id = ?";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(3, product.getId());
            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            int record = ps.executeUpdate();
            if(record >= 1) {
                result = true;
            } else {
                result = false;
            }
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    // ค้นหาข้อมูลโดยใช้ id และคืนค่าข้อมูลที่มีหมายเลข id ที่ตรงกันกลับไป
    public Product findById(int id) {
        Product product = new Product();
        String sql = "SELECT * FROM product WHERE id = ?";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            while(result.next()) {
                product.setId(result.getInt("id"));
                product.setName(result.getString("name"));
                product.setPrice(result.getInt("price"));
            }
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }
    // ลบข้อมูลตามหมายเลข id ที่ส่งมา
    public void delete(int id) {
        String sql = "DELETE FROM product WHERE id = ?";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    /*
    public static void main(String[] args) {
        // ทดสอบเรียกใช้เมธอด findAll()
        
//        ProductDao dao = new ProductDao();
//        List<Product> pList = dao.findAll();
//        
//        for (Product p : pList) {
//            System.out.println("สินค้า : " + p.toString());
//        }
        
        // ทดสอบเรียกใช้เมธอด add()
//        ProductDao dao = new ProductDao();
//        Product p = new Product("หลอดไฟ", 250);
//        boolean result = dao.add(p);
//        
//        if(result) {
//            System.out.println("บันทึกข้อมูลสำเร็จ");
//        }
        
        // ทดสอบเรียกใช้เมธอด edit()
//        ProductDao dao = new ProductDao();
//        Product p = new Product(4, "TV", 35000);
//        if(dao.edit(p)) {
//            System.out.println("แก้ไขข้อมูลสำเร็จ");
//        }
        // ทดสอบเรียกใช้เมธอด findById()
//        ProductDao dao = new ProductDao();
//        Product p = dao.findById(1);
//        System.out.println("Id 1 " + p.toString());
        
        // ทดสอบ ลบข้อมูล
//        ProductDao dao = new ProductDao();
//        dao.delete(15);
    }
    */
}
