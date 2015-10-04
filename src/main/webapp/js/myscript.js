/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


      var bodyheight = jQuery('body').height();
      var windowheight = jQuery(window).height();
      console.log(bodyheight);
      console.log(windowheight);

 if (bodyheight<windowheight){
        $(".bgfootimg").css("position", "absolute");
        $("#bar").css("position", "absolute");
        $("#bar").css("bottom", "80px");
    }