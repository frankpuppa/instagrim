/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


      var bodyheight = jQuery('body').height();
      var windowheight = jQuery(window).height();
      
      var wWidth= $(window).width();
      var barwidth= $("#bar").outerWidth(true);
      var center= (wWidth/2) - (barwidth/2); //some math to center the bar
    //  var sidebar=windowheight-280; //header +footer
      var containerHomeHeight=$("#AA").height();


//  console.log("DIV "+ containerHomeHeight);
    //console.log("sidebar"+sidebar);
//console.log("bodyheight" + bodyheight);
//console.log("windowheight"+windowheight)
//     console.log("barwidth " + barwidth);
//   console.log("bodywidth " + wWidth +"px");
//    console.log("center " + center +"px");
//          $("#sidebar-wrapper").css("height", (sidebar+"px"));

/*      SET DIV CONTAINER HEIGHT    */
$("#sidebar-wrapper").css("height", (containerHomeHeight+"px"));

/*      SET BOTTOM TOOLBAR AND FOOTER   */
if (bodyheight<windowheight){
      $(".bgfootimg").css("position", "absolute");
      $("#bar").css("position", "absolute");
      $("#bar").css("bottom", "80px");
      $("#bar").css("margin-left", center+"px" );
        }
        
// function hideShowClick() {
//     if($("#testtt").is(":visible")){
//         $("#testtt").hide();
//     }else{
//         $("#testtt").show();
//     }
//}
//function test(){
//    document.getElementById('form1').submit();
//  //  hideShowClick();
//}