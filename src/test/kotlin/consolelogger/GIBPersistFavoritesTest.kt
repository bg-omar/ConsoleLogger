package consolelogger

//
//import com.github.bgomar.consolelogger.services.GivServiceSettings
//import com.github.bgomar.consolebrowser.settings.FavoritesWeb
//import com.intellij.configurationStore.serialize
//import com.intellij.openapi.util.JDOMUtil
//import io.kotest.core.spec.style.BehaviorSpec
//import org.junit.Assert.assertEquals
//
//class GIBPersistFavoritesTest : BehaviorSpec() {
//
//  init {
//    given("test serialize") {
//      val service = GivServiceSettings()
//
//      service.addFavorite(
//        FavoritesWeb(webUrl = "https://127.0.0.1/")
//      )
//      val state = service.getState()
//      val element = serialize(state)!!
//      val xml = JDOMUtil.write(element)
//      then("validate xml") {
//        assertEquals("""<State>
//  <option name="favorites">
//    <list>
//      <FavoritesWeb>
//        <option name="webUrl" value="https://127.0.0.1/" />
//      </FavoritesWeb>
//    </list>
//  </option>
//</State>
//    """.trimIndent(), xml)
//      }
//    }
//
//  }
//
//}
