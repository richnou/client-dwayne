package com.dwaynecote

import org.odfi.wsb.fwapp.Site
import com.dwaynecote.login.LoginView
import com.dwaynecote.base.WelcomeView
import org.odfi.wsb.fwapp.lib.security.provider.google.GoogleAPIModule
import org.odfi.wsb.fwapp.lib.security.SecurityLibModule
import com.dwaynecote.account.AccountView
import org.odfi.wsb.fwapp.lib.security.rules.AuthenticatedRule

object DCSite extends Site("/site") {
  
  
  //-- Port 
  this.listen(8585)
  
  //-- Assets
  var assets = this.useDefaultAssets
  assets.addDefaultResourcesAssetSource(basePath)
  assets.addResourcesAssetSource("/semantic").addFilesSource("semantic-custom")
  
  // Google
  //------------
  requireModule(GoogleAPIModule)

  
  this.onStart {
    GoogleAPIModule.config.get.setString("clientID", "6094799447-rtc253q7groktbdprib66n4hfpcb16k0.apps.googleusercontent.com")
  }
  
  // Views
  //--------------
  
  //-- main
  view(new WelcomeView)
  
  //-- Login
  "/login" is {
    view(classOf[LoginView])
  }
  
  // Protected
  //-------------
  "/sp" is {
    rule (new AuthenticatedRule).redirection("/login")
    
    "/account" is {
      view(classOf[AccountView])
    }
  }
  
  
}