package com.dwaynecote.v1

import org.odfi.wsb.fwapp.module.semantic.SemanticView
import org.odfi.wsb.fwapp.lib.security.views.SecurityView
import org.odfi.wsb.fwapp.lib.markdown.MarkdownView
import org.odfi.wsb.fwapp.framework.FWAppValueBufferView
import com.dwaynecote.DwayneCote

class IntroPage extends SemanticView with MarkdownView with FWAppValueBufferView {

  this.viewContent {

    html {
      head {

        placeLibraries
        stylesheet(createAssetsResolverURI("/dc/dc/dc.css")) {

        }
        importHTML(<script src="//use.edgefonts.net/miama:n4.js"></script>)
      }
      body {

        "ui container grid" :: div {

          // header
          //--------------------
          "sixteen wide column #header" :: div {

            div {
              /* securitySessionGetUser match {
                case Some(user) =>
                  a("/account")(text(user.email))
                case None =>
                  a("/login")(text("Login"))
              }*/

            }

            /*"ui menu" :: div {

              "item" :: a("")(text("Biography"))
              "item" :: a("")(text("Discography"))
              "item" :: a("")(text("Lessons"))
            }*/
          }

          // Body
          //---------------
          "sixteen wide column #page" :: div {

            // Header
            //-------------
            "header" :: div {
              "main title" :: h1("Dwayne Cote") {

              }
              "second title" :: h1("A Cape Breton Fiddler") {

              }
              
              "pic" :: image(createAssetsResolverURI("/dc/dc/pictures/dwayne/fb-cover.jpg")) {
                
              }
            }

            // Listen and Biography components
            //---------------
            "ui grid #biography-container" :: div {
              "ten wide column" :: div {

                h3("Biography") {

                }

                markdown("""|
                           |""".stripMargin)

              }

              "six wide column" :: div {
                "listen ui segment" :: div {

                  h3("Listen")()

                  // h3("The Iona Sunrise")()
                  markdown("""|#### The Iona Sunrise Set
                          |
                          |This set of Dwayne's Composition follows a very 
                          |traditional structure you will find in Cape Breton 
                          |and Scottisch Music.
                          |
                          |It starts of with a slow air, called the Iona Sunrise, then switches to a moderate tempo Strasthspey,
                          |and ends with an up beat Reel.
                          |
                          |You can listen to this set, taken of the Album The Empress, which Dwayne recorded with
                          |Newfoundland's Guitarist Dwayne Andrews.  """.stripMargin)
                  div {
                    audio {
                      id("audio-demo")
                      +@("controls" -> "")
                      +@("volume" -> 0.2)

                      source {
                        +@("src" -> createAssetsResolverURI("/dc/dc/audio/iona.ogg"))
                        +@("type" -> "audio/ogg")
                      }
                      source {
                        +@("src" -> createAssetsResolverURI("/dc/dc/audio/iona.mp3"))
                        +@("type" -> "audio/mp3")
                      }
                    }
                  }
                  div {
                    "block" :: a("#") {
                      text("The Iona Sunrise (Dwayne Cote, SOCAN)")
                      +@("onclick" -> """document.getElementById("audio-demo").currentTime=0;return false;""")
                    }
                    "block" :: a("#") {
                      text("Strathspey (Dwayne Cote, SOCAN)")
                      +@("onclick" -> """document.getElementById("audio-demo").currentTime=60+57;return false;""")
                    }
                    "block" :: a("#") {
                      text("Reel (Dwayne Cote, SOCAN)")
                      +@("onclick" -> """document.getElementById("audio-demo").currentTime=120+48;return false;""")
                    }
                  }
                }
              }
            }

            // VIDEO
            //-----------

            //  width="1280" height="720"
            /*div {
              +@("style" -> ("width:70%;height:" + (70 * 9 / 16).toString() + "%;margin-left:auto;margin-right:auto"))

              importHTML(<iframe width="100%" height="100%" src="https://www.youtube.com/embed/4O0jcxjaUnk" frameborder="0" allowfullscreen="true"></iframe>)
            }*/

            // Lessons
            //------------------
            h3("Lessons") {

            }
            markdown("""
Dwayne is offering Lessons around the Sydney Area in Cape Breton, Nova Scotia, Cadana, or online via Skype.

#### Lessons by the Month

#### Lessons à la carte.

If a monthly subscription is not appropriate for you, for example if you want to work with Dwayne on a specific  set of skills,
you can choose to buy lessons à la carte.

These lessons can be freely planned with Dwayne, and be bought in sets of 5 or 10 lessons.

#### Tarif Formula

- Hourly Fee: 100 CAD
- Month Fee: 4 Lessons in a Month, 20% off
- A la carte: 10% off for 10 lessons, 5% off for 5
- Student: 20% off
- Children: 40% off
              
              """)

            div {
              //form {
              ul {
                li {
                  inputToBufferWithlabel("Hourly Fee:", DwayneCote.config.get.getIntAsBuffer("lessons.hour", 100)) {

                  }
                }
                li {
                  inputToBufferWithlabel("Percentage off for month", DwayneCote.config.get.getIntAsBuffer("lessons.month.off", 10)) {

                  }
                }
                li {
                  inputToBufferWithlabel("Percentage off for 10 Bundle", DwayneCote.config.get.getIntAsBuffer("lessons.bundle10.off", 10)) {

                  }
                }
                li {
                  inputToBufferWithlabel("Percentage off for 5 Bundle", DwayneCote.config.get.getIntAsBuffer("lessons.bundle5.off", 10)) {

                  }
                }
                li {
                  inputToBufferWithlabel("Percentage off for Students", DwayneCote.config.get.getIntAsBuffer("lessons.student.off", 10)) {

                  }
                }
                li {
                  inputToBufferWithlabel("Percentage off for Children", DwayneCote.config.get.getIntAsBuffer("lessons.children.off", 10)) {

                  }
                }
              }
              "ui primary button" :: buttonClickReload("Update") {
                DwayneCote.saveConfig
              }
              // semanticOnSubmitButton("Update") {

              // }
              // }
            }

            val hour = DwayneCote.config.get.getInt("lessons.hour", 100)
            val month = (hour * 4 * (100 - DwayneCote.config.get.getInt("lessons.month.off", 10)) / 100)
            val carte10 = hour * 10 * (100 - DwayneCote.config.get.getInt("lessons.bundle10.off", 10)) / 100
            val carte5 = hour * 5 * (100 - DwayneCote.config.get.getInt("lessons.bundle5.off", 10)) / 100

            def student(p: Int) = p * (100 - DwayneCote.config.get.getInt("lessons.student.off", 10)) / 100
            def child(p: Int) = p * (100 - DwayneCote.config.get.getInt("lessons.children.off", 10)) / 100
            val prices = Map(

              "Month" -> Map("A" -> month, "S" -> student(month), "C" -> child(month)),
              "Carte10" -> Map("A" -> carte10, "S" -> student(carte10), "C" -> child(carte10)),
              "Carte5" -> Map("A" -> carte5, "S" -> student(carte5), "C" -> child(carte5)))

            // Offer Table
            //-----------------

            // Prices Formula

            "ui definition stripped table" :: table {

              thead("", "60 minutes", "30 minutes")

              def namePriceLine(n: String, p: (Int, Int)) = {
                tr {
                  td(n)()
                  rtd {
                    a("#") {
                      text(p._1.toString + " C.A.D")
                      +@("onclick" -> s"""$$("#gcw_valFrWekbsJv0").val(${p._1.toString}).change();return false;""")
                    }
                  }
                  rtd {
                    a("#") {
                      text(p._2.toString + " C.A.D")
                      +@("onclick" -> s"""$$("#gcw_valFrWekbsJv0").val(${p._2.toString}).change();return false;""")
                    }
                  }

                }
              }

              tbody {
                "spanline" :: tr {
                  td("")()
                  td("Monthly Payment") {
                    colspan(2)
                  }
                }

                var values = List(
                  "Adults" -> (prices("Month")("A"), prices("Month")("A") / 2),
                  "Students" -> (prices("Month")("S"), prices("Month")("S") / 2),
                  "Children" -> (prices("Month")("C"), prices("Month")("C") / 2))
                values.foreach(p => namePriceLine(p._1, p._2))

                "spanline" :: tr {
                  td("")()
                  td("Bundle lessons (x10)") {
                    colspan(2)
                  }
                }
                values = List(
                  "Adults" -> (prices("Carte10")("A"), prices("Carte10")("A") / 2),
                  "Students" -> (prices("Carte10")("S"), prices("Carte10")("S") / 2),
                  "Children" -> (prices("Carte10")("C"), prices("Carte10")("C") / 2))
                values.foreach(p => namePriceLine(p._1, p._2))

                "spanline" :: tr {
                  td("")()
                  td("Bundle lessons (x5)") {
                    colspan(2)
                  }
                }
                values = List(
                  "Adults" -> (prices("Carte5")("A"), prices("Carte5")("A") / 2),
                  "Students" -> (prices("Carte5")("S"), prices("Carte5")("S") / 2),
                  "Children" -> (prices("Carte5")("C"), prices("Carte5")("C") / 2))
                values.foreach(p => namePriceLine(p._1, p._2))
              }

            }
            // EOF Table

            div {
              importHTML {
                <div>
                  <div id='gcw_mainFrWekbsJv' class='gcw_mainFrWekbsJv'></div>
                  <a id='gcw_siteFrWekbsJv' href='https://freecurrencyrates.com/en/'>FreeCurrencyRates.com</a>
                  <!-- put custom styles here: .gcw_mainFrWekbsJv{}, .gcw_headerFrWekbsJv{}, .gcw_ratesFrWekbsJv{}, .gcw_sourceFrWekbsJv{} -->
                  <!--End of Currency Converter widget by FreeCurrencyRates.com -->
                </div>
              }
              script("""
                function reloadFrWekbsJv(){ var sc = document.getElementById('scFrWekbsJv');if (sc) sc.parentNode.removeChild(sc);sc = document.createElement('script');sc.type = 'text/javascript';sc.charset = 'UTF-8';sc.async = true;sc.id='scFrWekbsJv';sc.src = 'https://freecurrencyrates.com/en/widget-horizontal-editable?iso=CADXUM&df=2&p=FrWekbsJv&v=it&source=fcr&width=600&width_title=200&firstrowvalue=1&thm=cccccc,F9F9F9,A3A3A3,333333,eeeeee,cccccc,ffffff,222222,000000&title=Currency%20Converter&tzo=-120';var div = document.getElementById('gcw_mainFrWekbsJv');div.parentNode.insertBefore(sc, div);} reloadFrWekbsJv();
                
                """)
            }
          }

          // Footer
          //-------------------

        }

      }
    }

  }

}