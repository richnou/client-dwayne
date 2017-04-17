package com.dwaynecote

import org.odfi.indesign.core.module.IndesignModule
import org.odfi.indesign.core.main.IndesignPlatorm
import org.odfi.indesign.core.config.ooxoo.OOXOOConfigModule
import java.io.File
import org.odfi.wsb.fwapp.DefaultSiteApp
import org.odfi.wsb.fwapp.lib.security.rules.AuthenticatedRule
import com.dwaynecote.login.LoginView
import com.dwaynecote.base.WelcomeView
import com.dwaynecote.account.AccountView
import org.odfi.wsb.fwapp.lib.security.provider.google.GoogleAPIModule
import com.dwaynecote.v1.IntroPage
import org.odfi.wsb.fwapp.Site
import org.odfi.indesign.ide.module.maven.project

object DwayneCote extends DefaultSiteApp("/site") {

  //tlogEnableFull[Site]
  
  //-- Port 
  val pro = project("com.idyria.web","dwaynecote")
  pro.get.isSnapshot match {
    case true => 
      
       this.listen(sys.env.getOrElse("port", "8586").toInt)
       
    case false => 
       this.listen(sys.env.getOrElse("port", "8585").toInt)
  }
 

  //-- Assets
  //var assets = this.useDefaultAssets
  assetsManager.addDefaultResourcesAssetSource("/dc")
  //assets.addDefaultResourcesAssetSource(basePath)
  //assets.addResourcesAssetSource("/semantic").addFilesSource("semantic-custom")

  // Google
  //------------
  requireModule(GoogleAPIModule)

  this.onStart {
    GoogleAPIModule.config.get.setString("clientID", "6094799447-rtc253q7groktbdprib66n4hfpcb16k0.apps.googleusercontent.com")
  }

  // Views
  //--------------

  //-- main
  //view(new WelcomeView)
  //view(new IntroPage)
  view(classOf[IntroPage])
  
  
  //-- Login
  "/login" is {
    view(classOf[LoginView])
  }

  // Protected
  //-------------
  "/sp" is {
    rule(new AuthenticatedRule).redirection("/login")

    "/account" is {
      view(classOf[AccountView])
    }
  }

  //------------------------
  // Start
  //------------------------
  //IndesignPlatorm use OOXOOConfigModule
  //OOXOOConfigModule.configFolder = new File("dwayne-config")
  start
}