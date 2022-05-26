package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.Product;
import dal.ProductDAO;
import model.Category;
import java.util.List;
import dal.CategoryDAO;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items_end_begin;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_import_url_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_var_items_end_begin = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_import_url_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_var_items_end_begin.release();
    _jspx_tagPool_c_import_url_nobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <title>Home</title>\n");
      out.write("        <script src=\"js/set-theme.js\"></script>\n");
      out.write("        <link href=\"https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css\" rel=\"stylesheet\" integrity=\"sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN\" crossorigin=\"anonymous\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/main.css\">\n");
      out.write("        <script src=\"js/bootstrap/jquery.min.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "element/navbar.jsp", out, false);
      out.write("\n");
      out.write("        <!-- Banner -->\n");
      out.write("        <div class=\"rei_banner_container\">\n");
      out.write("            <div class=\"rei_banner_body\">\n");
      out.write("                <div>\n");
      out.write("                    <a href=\"product?categoryID=1\"><img src=\"img/banner_01.jpg\"></a>\n");
      out.write("                </div>\n");
      out.write("                <div>\n");
      out.write("                    <a href=\"product?categoryID=2\"><img src=\"img/banner_02.jpg\"></a>\n");
      out.write("                </div>\n");
      out.write("                <div>\n");
      out.write("                    <a href=\"product?categoryID=3\"><img src=\"img/banner_03.jpg\"></a>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"rei_banner_bottombtns\">\n");
      out.write("                <span onclick=\"getBanner(1)\" style='background-image:url(img/banner_01.jpg)'></span>\n");
      out.write("                <span onclick=\"getBanner(2)\" style='background-image:url(img/banner_02.jpg)'></span>\n");
      out.write("                <span onclick=\"getBanner(3)\" style='background-image:url(img/banner_03.jpg)'></span>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"home-show col-ms-12 col-lg-10\">\n");
      out.write("            <form class=\"search-form\" action=\"product\">\n");
      out.write("                <input type=\"text\" id=\"search\" class=\"search-input\" name=\"searchkey\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.searchkey}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" placeholder=\"Search\" autocomplete=\"off\">\n");
      out.write("                <div class=\"search-icon\"><i class=\"fa fa-search\" aria-hidden=\"true\"></i></div>\n");
      out.write("            </form>\n");
      out.write("            \n");
      out.write("            <!-- Best Seller -->\n");
      out.write("            <div class=\"home-block\">\n");
      out.write("                <div class=\"slide bestseller\">\n");
      out.write("                    <h2>Bestseller</h2>\n");
      out.write("                    <div class=\"product-home row mrg-lr-0\">\n");
      out.write("                        ");
      if (_jspx_meth_c_forEach_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                    <button class=\"left-btn\"><</button>\n");
      out.write("                    <button class=\"right-btn\">></button>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <!-- Mini Banner -->\n");
      out.write("            <div class=\"mini-banner\">\n");
      out.write("                <div class=\"row mrg-lr-0\">\n");
      out.write("                    <div class=\"col-4\">\n");
      out.write("                        <a class=\"button\" href=\"product?categoryID=1\">Men</a>\n");
      out.write("                        <a href=\"product?categoryID=1\">\n");
      out.write("                            <img src=\"img/men.jpg\">\n");
      out.write("                        </a>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-4\">\n");
      out.write("                        <a class=\"button\" href=\"product?categoryID=2\">Women</a>\n");
      out.write("                        <a href=\"product?categoryID=2\">\n");
      out.write("                            <img src=\"img/women.jpg\">\n");
      out.write("                        </a>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-4\">\n");
      out.write("                        <a class=\"button\" href=\"product?categoryID=3\">Kid</a>\n");
      out.write("                        <a href=\"product?categoryID=3\">\n");
      out.write("                            <img src=\"img/kid.jpg\">\n");
      out.write("                        </a>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <!-- All Category -->\n");
      out.write("            <div class=\"home-block allcategory\">\n");
      out.write("                <div>\n");
      out.write("                    <ul>\n");
      out.write("                        <li><button class=\"focus\" name=\"categoryID\" onclick=\"moveTab('.product_cate_0')\">All</button></li>  \n");
      out.write("                        ");

                            CategoryDAO c_dao = new CategoryDAO();
                            List<Category> categoryList = c_dao.getAllCategory();
                            for (Category category : categoryList) {
                        
      out.write("\n");
      out.write("                        <li><button name=\"categoryID\" onclick=\"moveTab('.product_cate_");
      out.print( category.getCategoryID());
      out.write("')\">");
      out.print( category.getCategoryName() );
      out.write("</button></li>  \n");
      out.write("                        ");

                            }
                        
      out.write("\n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"tab slide product_cate_0\">\n");
      out.write("                    <div class=\"product-home row mrg-lr-0\">\n");
      out.write("                        ");
      if (_jspx_meth_c_forEach_1(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                    <button class=\"left-btn\"><</button>\n");
      out.write("                    <button class=\"right-btn\">></button>\n");
      out.write("                    <div class=\"extend-link\">\n");
      out.write("                        <a href=\"product\">See more...</a>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                ");

                    for (Category category : categoryList) {
                
      out.write("\n");
      out.write("                <div class=\"tab slide product_cate_");
      out.print( category.getCategoryID());
      out.write("\">\n");
      out.write("                    <div class=\"product-home row mrg-lr-0\">\n");
      out.write("                    ");

                            ProductDAO p_dao = new ProductDAO();
                            List<Product> productList = p_dao.getProducts(category.getCategoryID());
                            int start=0;
                            for (Product product : productList) {
                                if(start<12){
                    
      out.write("\n");
      out.write("                        <div class=\"product-list product col-1\">\n");
      out.write("                            <div class=\"product-image\">\n");
      out.write("                                <a href=\"product?action=details&productID=");
      out.print( product.getProductID() );
      out.write("\"><img src=\"images/");
      out.print( product.getImage());
      out.write("\" alt=\"\"></a>\n");
      out.write("                            </div>\n");
      out.write("                            <p class=\"product-name\">");
      out.print( product.getProductName());
      out.write("</p>\n");
      out.write("                            <div>\n");
      out.write("                                <p><b class=\"price\">$");
      out.print( product.getPrice());
      out.write("</b></p>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"add-btn\">\n");
      out.write("                                <a class=\"link-button\" href=\"addtocart?productID=");
      out.print( product.getProductID() );
      out.write("\">Add to Cart</a>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    ");

                                }
                                start++;
                            }
                    
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                    <button class=\"left-btn\"><</button>\n");
      out.write("                    <button class=\"right-btn\">></button>\n");
      out.write("                    <div class=\"extend-link\">\n");
      out.write("                        <a href=\"product?categoryID=");
      out.print( category.getCategoryID());
      out.write("\">See more...</a>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                ");

                    }
                
      out.write("\n");
      out.write("                \n");
      out.write("            </div>\n");
      out.write("            <script>\n");
      out.write("                var x = document.querySelectorAll('.allcategory .tab');\n");
      out.write("                for (var i = 0; i < x.length; i++) {\n");
      out.write("                    x[i].style = \"display: none\";\n");
      out.write("                }\n");
      out.write("                document.querySelector('.product_cate_0').style = '';\n");
      out.write("                function moveTab(b) {\n");
      out.write("                    for (var i = 0; i < x.length; i++) {\n");
      out.write("                        x[i].style = \"display: none\";\n");
      out.write("                    }\n");
      out.write("                    document.querySelector(b).style = '';\n");
      out.write("                }\n");
      out.write("                \n");
      out.write("                var y = document.querySelectorAll('.allcategory ul button');\n");
      out.write("                y.forEach((b) => {\n");
      out.write("                    \n");
      out.write("                    b.addEventListener('click', function(){\n");
      out.write("                        for(var i=0; i<y.length; i++) {\n");
      out.write("                            y[i].classList = '';\n");
      out.write("                        }\n");
      out.write("                        b.classList='focus';\n");
      out.write("                    });\n");
      out.write("                });\n");
      out.write("            </script>\n");
      out.write("        </div>\n");
      out.write("        ");
      if (_jspx_meth_c_import_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("    </body>\n");
      out.write("    <script src=\"js/banner.js\"></script>\n");
      out.write("    <script>\n");
      out.write("        document.querySelectorAll('.home-block>.slide').forEach((d) => {\n");
      out.write("            var index=0;\n");
      out.write("            var area = d.querySelector('.product-home');\n");
      out.write("            d.querySelector('.left-btn').addEventListener('click', function(){\n");
      out.write("                if(index!=0){\n");
      out.write("                    index--;\n");
      out.write("                    area.style = 'transform: translateX(-'+ index*33.3333333333 +'%);';\n");
      out.write("                }\n");
      out.write("            });\n");
      out.write("            d.querySelector('.right-btn').addEventListener('click', function(){\n");
      out.write("                if(index!=2){\n");
      out.write("                    index++;\n");
      out.write("                    area.style = 'transform: translateX(-'+ index*33.3333333333 +'%);';\n");
      out.write("                }\n");
      out.write("            });\n");
      out.write("        });\n");
      out.write("    </script>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_forEach_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items_end_begin.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent(null);
    _jspx_th_c_forEach_0.setBegin(0);
    _jspx_th_c_forEach_0.setEnd(11);
    _jspx_th_c_forEach_0.setVar("product");
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.bestseller_productlist}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                            <div class=\"product col-1\">\n");
          out.write("                                <div class=\"product-image\">\n");
          out.write("                                    <a href=\"product?action=details&productID=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${product.productID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\"><img src=\"images/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${product.image}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" alt=\"\"></a>\n");
          out.write("                                </div>\n");
          out.write("                                <p class=\"product-name\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${product.productName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</p>\n");
          out.write("                                <div>\n");
          out.write("                                    <p><b class=\"price\">$");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${product.price}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</b></p>\n");
          out.write("                                </div>\n");
          out.write("                                <div class=\"add-btn\">\n");
          out.write("                                    <a class=\"link-button\" href=\"addtocart?productID=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${product.productID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\">Add to Cart</a>\n");
          out.write("                                </div>\n");
          out.write("                            </div>\n");
          out.write("                        ");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_var_items_end_begin.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }

  private boolean _jspx_meth_c_forEach_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items_end_begin.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_1.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_1.setParent(null);
    _jspx_th_c_forEach_1.setBegin(0);
    _jspx_th_c_forEach_1.setEnd(11);
    _jspx_th_c_forEach_1.setVar("product");
    _jspx_th_c_forEach_1.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.productlist_all}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int[] _jspx_push_body_count_c_forEach_1 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_1 = _jspx_th_c_forEach_1.doStartTag();
      if (_jspx_eval_c_forEach_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                            <div class=\"product col-1\">\n");
          out.write("                                <div class=\"product-image\">\n");
          out.write("                                    <a href=\"product?action=details&productID=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${product.productID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\"><img src=\"images/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${product.image}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\" alt=\"\"></a>\n");
          out.write("                                </div>\n");
          out.write("                                <p class=\"product-name\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${product.productName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</p>\n");
          out.write("                                <div>\n");
          out.write("                                    <p><b class=\"price\">$");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${product.price}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</b></p>\n");
          out.write("                                </div>\n");
          out.write("                                <div class=\"add-btn\">\n");
          out.write("                                    <a class=\"link-button\" href=\"addtocart?productID=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${product.productID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\">Add to Cart</a>\n");
          out.write("                                </div>\n");
          out.write("                            </div>\n");
          out.write("                        ");
          int evalDoAfterBody = _jspx_th_c_forEach_1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_1.doFinally();
      _jspx_tagPool_c_forEach_var_items_end_begin.reuse(_jspx_th_c_forEach_1);
    }
    return false;
  }

  private boolean _jspx_meth_c_import_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:import
    org.apache.taglibs.standard.tag.rt.core.ImportTag _jspx_th_c_import_0 = (org.apache.taglibs.standard.tag.rt.core.ImportTag) _jspx_tagPool_c_import_url_nobody.get(org.apache.taglibs.standard.tag.rt.core.ImportTag.class);
    _jspx_th_c_import_0.setPageContext(_jspx_page_context);
    _jspx_th_c_import_0.setParent(null);
    _jspx_th_c_import_0.setUrl("element/footer.jsp");
    int[] _jspx_push_body_count_c_import_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_import_0 = _jspx_th_c_import_0.doStartTag();
      if (_jspx_th_c_import_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_import_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_import_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_import_0.doFinally();
      _jspx_tagPool_c_import_url_nobody.reuse(_jspx_th_c_import_0);
    }
    return false;
  }
}
