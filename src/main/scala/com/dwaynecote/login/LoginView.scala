package com.dwaynecote.login

import com.dwaynecote.base.DCBaseUI
import org.odfi.wsb.fwapp.lib.markdown.MarkdownView
import org.odfi.wsb.fwapp.lib.security.provider.google.GoogleSigninView
import org.odfi.wsb.fwapp.navigation.NavigationUtilsView

class LoginView extends DCBaseUI with GoogleSigninView with MarkdownView with NavigationUtilsView {

  placePage {
    h1("Login") {

    }

    semanticDivider
    // Signing or Register
    //-------------------
    securitySessionGetUser match {

      case Some(user) if (!user.isPermanent) =>

        markdown("""| ## Register
                    | 
                    | You have successfully signed in, but it seems it is the first time you visit our site.
                    | If you fill in the following form, we will save your information and remember you when you are returning.
                    | """.stripMargin)

        // Test form
        "ui form" :: form {

          "field" :: div {
            label("Name") {

            }

            "two fields" :: div {
              "field" :: div {
                input {
                  semanticFieldRequire
                  fieldName("name")
                }
              }
            }
          }

          "ui primary submit button" :: div {
            text("Submit")
          }

          onSubmit {

            println(s"Saving User")
            securitySessionSaveUser

          }
          //importHTML(<div class="ui primary submit button" tabindex="0">Submit</div>)
          importHTML(<div class="ui error message"></div>)
        }

      case Some(user) if (user.isPermanent) =>

        markdown("""| ## Welcome!
                    | 
                    | You have successfully signed in and are registered, use this Page to manage your account
                    | """.stripMargin)
        redirectToOrigin

      case other =>
        markdown("""|
                    |## Sign In: First time user
                    | 
                    |Dear user, using this page you can login to this website and register if you are a first time visitor,
                    |or manage your account if you are returning.
                    |  
                    |To Identify to our services, you can use one of the listed providers: 
                    | 
                    |  - Google
                    |  - Facebook
                    |  - Mozilla Persona
                    |  - OpenID
                    |
                    |
                    |To protected your privacy, we are not requesting any personal details from this services besides your email address.
                    | 
                    |Using those providers to Identify allows us to not have to store any password information on our service, and prevents
                    |you from having to create and remember yet another one.  
                    | 
                    |## Sign In: Returning Customer
                    | 
                    |If you are returning to this website, make sure you are using the same identification provider as the last time.
                    |If you don't remember anymore, use the "I Forgot my Provider" link, we will send you an email with a reminder.
                    | 
                    |If you wish to use another provider, identify with the current one, and use your account management page to switch.
                    | 
                    | 
                    |""".stripMargin)
        googleSigninButton

    }

  }

}