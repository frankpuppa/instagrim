/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


      var bodyheight = jQuery('body').height();
      var windowheight = jQuery(window).height();
      
      var wWidth= $(window).width();
      var barwidth= $("#bar").outerWidth(true);
      var center= (wWidth/2) - (barwidth/2);
//     // console.log(bodyheight);
//      //console.log(windowheight);
//      console.log("barwidth " + barwidth);
//     console.log("bodywidth " + wWidth +"px");
//     console.log("center " + center +"px");


 if (bodyheight<windowheight){
      $(".bgfootimg").css("position", "absolute");
      $("#bar").css("position", "absolute");
      $("#bar").css("bottom", "80px");
      $("#bar").css("margin-left", center );
        }