package com.dwaynecote.base

import org.odfi.wsb.fwapp.module.semantic.SemanticView
import org.odfi.wsb.fwapp.lib.security.views.SecurityView

trait DCBaseUI extends SemanticView with SecurityView {

  def placePage(cl: => Any) = {
    this.definePart("page-content")(div(cl))
  }

  this.viewContent {

    html {
      head {
        placeLibraries
      }
      body {

        "ui container grid" :: div {

          // header
          //--------------------
          "sixteen wide column #header" :: div {

            div {
              securitySessionGetUser match {
                case Some(user) =>
                  a("/account")(text(user.email))
                case None =>
                  a("/login")(text("Login"))
              }

              
            }

            "ui menu" :: div {

              "item" :: a("")(text("Biography"))
              "item" :: a("")(text("Discography"))
              "item" :: a("")(text("Lessons"))
            }
          }

          // Body
          //---------------
          "sixteen wide column #page" :: div {
            placePart("page-content")
          }

          // Footer
          //-------------------

        }

      }
    }

  }

}