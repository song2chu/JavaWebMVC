<%-- 
    Document   : edit_product
    Created on : Sep 21, 2014, 11:42:39 AM
    Author     : Praew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>แก้ไขสินค้า</title>
    </head>
    <body>
        <h1>แก้ไขสินค้า</h1>
        <form action="EditProduct" method="POST">
            <table>
                <tbody>
                    <tr>
                        <td>ชื่อสินค้า : </td>
                        <td><input type="text" name="name" value="${product.name}" /></td>
                    </tr>
                    <tr>
                        <td>ราคา : </td>
                        <td><input type="text" name="price" value="${product.price}" /></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="hidden" name="id" value="${product.id}" />
                            <input type="submit" value="แก้ไข" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
