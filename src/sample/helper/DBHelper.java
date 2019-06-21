package sample.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.entity.Cart;
import sample.entity.Customer;
import sample.entity.Foods;

import java.sql.*;

public class DBHelper {

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Config config = new Config();
        String stringConnection;
        Connection connection;

        stringConnection = "jdbc:mysql://" + config.getDbHost() + ":"
                + config.getDbPort() + "/" + config.getDbName() + "?autoReconnect=true&useSSL=false";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(stringConnection, config.getDbUser(), config.getDbPass());

        return connection;
    }

    public ObservableList<Foods> getListItems(Connection connection, int type) {
        String query = "SELECT * FROM items WHERE type=?";
        ResultSet resultSet;
        ObservableList<Foods> mList = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, type);
            resultSet = statement.executeQuery();
            mList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                Foods mFood = new Foods(
                        resultSet.getInt("id_items"),
                        resultSet.getString("name"),
                        resultSet.getString("desc"),
                        resultSet.getInt("price"),
                        resultSet.getFloat("rate"),
                        resultSet.getString("img_url"),
                        resultSet.getInt("status")
                );
                mList.add(mFood);
            }
        } catch (Exception e) {
            System.out.println("DBHelper Exc : " + e.getMessage());
        }
        return mList;
    }

    public ObservableList<Cart> getItemsCart(Connection connection) {
        String query = "SELECT * FROM cart";
        ResultSet resultSet;
        ObservableList<Cart> mList = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            mList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                Cart mCart = new Cart(
                        resultSet.getInt("id_chart"),
                        resultSet.getString("img_url"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getInt("qty")
                );
                mList.add(mCart);
            }
        } catch (Exception e) {
            System.out.println("DBHelper Exc : " + e.getMessage());
        }
        return mList;
    }


    public ObservableList<Foods> sortItemBy(Connection connection, int type, int order) {
        String query = null;
        switch (order) {
            case 0:
                query = "SELECT * FROM items WHERE type=? ORDER BY rate DESC";
                break;
            case 1:
                query = "SELECT * FROM items WHERE type=? ORDER BY price DESC";
                break;
            case 2:
                query = "SELECT * FROM items WHERE type=? ORDER BY price ASC";
                break;
        }
        ResultSet resultSet;
        ObservableList<Foods> mList = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, type);
            resultSet = statement.executeQuery();
            mList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                Foods mFood = new Foods(
                        resultSet.getInt("id_items"),
                        resultSet.getString("name"),
                        resultSet.getString("desc"),
                        resultSet.getInt("price"),
                        resultSet.getFloat("rate"),
                        resultSet.getString("img_url"),
                        resultSet.getInt("status")
                );
                mList.add(mFood);
            }
        } catch (Exception e) {
            System.out.println("DBHelper Exc : " + e.getMessage());
        }
        return mList;
    }

    public Customer getLastCustomer(Connection connection) {
        String query = "SELECT * FROM customer ORDER BY id_customer DESC LIMIT 1";
        ResultSet resultSet;
        Customer mCustomer = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                mCustomer = new Customer(
                        resultSet.getInt("id_customer"),
                        resultSet.getString("name"),
                        resultSet.getInt("kursi"),
                        resultSet.getInt("items"),
                        resultSet.getInt("total"),
                        resultSet.getInt("stat")
                );

            }
        } catch (Exception e) {
            System.out.println("DBHelper Exc : " + e.getMessage());
        }
        return mCustomer;
    }

    public void setCustomerName(Connection connection, String name) {
        try {
            String query = "INSERT INTO customer (name, stat) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setInt(2, 0);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("DBHelper Exc : " + e.getMessage());
        }
    }


    public int getIdCustomer(Connection connection) {
        String query = "SELECT id_customer FROM customer ORDER BY id_customer DESC LIMIT 1";
        int id = 0;
        ResultSet resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id_customer");
            }
        } catch (Exception e) {
            System.out.println("DBHelper Exc : " + e.getMessage());
        }
        return id;
    }

    public String getNameCustomer(Connection connection) {
        String query = "SELECT name FROM customer ORDER BY id_customer DESC LIMIT 1";
        String name = null;
        ResultSet resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                name = resultSet.getString("name");
            }
        } catch (Exception e) {
            System.out.println("DBHelper Exc : " + e.getMessage());
        }
        return name;
    }

    public int getChairCustomer(Connection connection) {
        String query = "SELECT kursi FROM customer ORDER BY id_customer DESC LIMIT 1";
        int chair = 0;
        ResultSet resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                chair = resultSet.getInt("kursi");
            }
        } catch (Exception e) {
            System.out.println("DBHelper Exc : " + e.getMessage());
        }
        return chair;
    }

    public void setChairNum(Connection connection, int chair, int id) {
        try {
            String query2 = "UPDATE customer SET kursi = ? WHERE id_customer = ?";
            PreparedStatement statement = connection.prepareStatement(query2);
            statement.setInt(1, chair);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("DBHelper Exc : " + e.getMessage());
        }
    }

    public void setCustomerPay(Connection connection, int item, int pay, int id) {
        try {
            String query2 = "UPDATE customer SET items = ?, total = ? WHERE id_customer = ?";
            PreparedStatement statement = connection.prepareStatement(query2);
            statement.setInt(1, item);
            statement.setInt(2, pay);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("DBHelper setCustomerPay Exc : " + e.getMessage());
        }
    }

    public void updatePrice(Connection connection, Cart mCart) {
        try {
            String query2 = "UPDATE cart SET price = ? WHERE id_chart = ?";
            PreparedStatement statement = connection.prepareStatement(query2);
            statement.setInt(1, mCart.getTotalPrice());
            statement.setInt(2, mCart.getId());
            statement.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void updateQty(Connection connection, Cart mCart) {
        try {
            String query2 = "UPDATE cart SET qty = ? WHERE id_chart = ?";
            PreparedStatement statement = connection.prepareStatement(query2);
            statement.setInt(1, mCart.getQty());
            statement.setInt(2, mCart.getId());
            statement.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void updateStatus(Connection connection, int id, int stat) {
        try {
            String query = "UPDATE items SET status = ? WHERE id_items = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, stat);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void setStatustoDefault(Connection connection) {
        try {
            String query = "UPDATE items SET status = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, 0);
            statement.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void addItemstoCart(Connection connection, Foods foods, int qty) {
        try {
            String query = "INSERT INTO cart (id_chart,img_url, name, price, qty) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, foods.getId());
            statement.setString(2, foods.getImg());
            statement.setString(3, foods.getName());
            statement.setInt(4, foods.getPrice());
            statement.setInt(5, qty);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("DBHelper Exc : " + e.getMessage());
        }
    }

    public void addtoCustomerLog(Connection connection, Customer mCustomer) {
        try {
            String query = "INSERT INTO customerlog (id_customer, name, kursi, items, total, stat) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, mCustomer.getId());
            statement.setString(2, mCustomer.getName());
            statement.setInt(3, mCustomer.getChairnum());
            statement.setInt(4, mCustomer.getTotalItems());
            statement.setInt(5, mCustomer.getTotalPay());
            statement.setInt(6, mCustomer.getStatus());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("DBHelper Exc : " + e.getMessage());
        }
    }

    public void deleteItemsCartbyId(Connection connection, int id) {
        try {
            String query = "DELETE FROM cart WHERE id_chart = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("DBHelper deleteItemsCartbyId Exc : " + e.getMessage());
        }
    }

    public void deleteLastCustomer(Connection connection) {
        try {
            String query = "DELETE FROM customer ORDER BY id_customer DESC LIMIT 1";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("DBHelper Exc : " + e.getMessage());
        }
    }

    public void deleteAllItemsCart(Connection connection) {
        try {
            String query = "DELETE FROM cart";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("DBHelper deleteAllItemsCart Exc : " + e.getMessage());
        }
    }

    public int getTotalPrice(Connection connection) {
        String query = "SELECT SUM(price) AS 'total' FROM cart";
        int total = 0;
        ResultSet resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                total = resultSet.getInt("total");
            }
        } catch (Exception e) {
            System.out.println("DBHelper Exc : " + e.getMessage());
        }
        return total;
    }

    public int getTotalPay(Connection connection) {
        String query = "SELECT SUM(price*qty) AS 'total' FROM cart";
        int total = 0;
        ResultSet resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                total = resultSet.getInt("total");
            }
        } catch (Exception e) {
            System.out.println("DBHelper Exc : " + e.getMessage());
        }
        return total;
    }

    public int getTotalItems(Connection connection) {
        String query = "SELECT SUM(qty) AS 'total' FROM cart";
        int total = 0;
        ResultSet resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                total = resultSet.getInt("total");
            }
        } catch (Exception e) {
            System.out.println("DBHelper Exc : " + e.getMessage());
        }
        return total;
    }

    public int getTotalItemCart(Connection connection) {
        String query = "SELECT COUNT(id_chart) AS 'total' FROM cart";
        int total = 0;
        ResultSet resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                total = resultSet.getInt("total");
            }
        } catch (Exception e) {
            System.out.println("DBHelper Exc : " + e.getMessage());
        }
        return total;
    }


}
