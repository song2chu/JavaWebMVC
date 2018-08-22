<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>จัดการสินค้า</title>
        <link rel="stylesheet" href="css/style.css" type="text/css"/>
        <script>
            function validateForm()
            {
                if (document.frm.name.value == "")
                {
                    alert("กรุณากรอกชื่อสินค้า");
                    document.frm.name.focus();
                    return false;
                }
                else if (document.frm.price.value == "")
                {
                    alert("กรุณากรอกราคาสินค้า");
                    document.frm.price.focus();
                    return false;
                }
            }

            function confirmDelete() {
                var del = window.confirm("ยืนยันการลบ");
                return del;
            }

        </script>
    </head>
    <body>
        <h1>Manage Product</h1>
        <c:if test="${ confirm_alert != null}">
            ${confirm_alert}
        </c:if>
        <div id="add_product">
            <h3>เพิ่มสินค้า</h3>
            <form name="frm" action="AddProduct" method="POST" onSubmit="return validateForm()">
                <table>
                    <tbody>
                        <tr>
                            <td>ชื่อ</td>
                            <td><input type="text" name="name"/></td>
                        </tr>
                        <tr>
                            <td>ราคา</td>
                            <td><input type="text" name="price"/></td>
                        </tr>
                        <tr >
                            <td colspan="2" align="center">
                                <input type="submit" value="บันทึก" />
                                <input type="reset" value="ยกเลิก" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
        <br><br>
        <table width="50%" border="1">
            <thead>
                <tr>
                    <th width="5%">ลำดับ</th>
                    <th width="20%">ชื่อสินค้า</th>
                    <th width="10%">ราคา</th>
                    <th width="5%">แก้ไข</th>
                    <th width="5%">ลบ</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${productList}" var="p" varStatus="i">  
                    <tr>
                        <td align="center">${i.count}</td>
                        <td>${p.name}</td>
                        <td align="right">
                            <fmt:formatNumber pattern="#,###.00">
                                ${p.price}
                            </fmt:formatNumber>
                        </td>
                        <td align="center">
                            <a href="Edit?id=${p.id}">
                                <img src="/JavaWebMVC/images/edit.png" width="30" height="30">
                            </a>
                        </td>
                        <td align="center">
                            <a href="Delete?id=${p.id}" onclick="return confirmDelete();">
                                <img src="/JavaWebMVC/images/delete.png" width="30" height="30">
                            </a>
                        </td>
                    </tr>
                </c:forEach>  
            </tbody>
        </table>
        <br>
        <b>รวม : ${fn:length(productList)}</b>
    </body>
</html>
