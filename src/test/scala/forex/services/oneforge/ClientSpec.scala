package forex.services.oneforge
import forex.config.OneForgeConfig
import forex.domain.{Currency, Rate}
import org.scalatest.AsyncWordSpec

class ClientSpec extends AsyncWordSpec {

  "The 1Forge client" should {

    "return Rate with correct syntax" in {

      val client = new Client(
        OneForgeConfig(
          "4Zc8xsXNAU3N8eO1sHF83wkQO00buyKL",
          "https://forex.1forge.com/1.0.3"
        )
      )

      val from = Currency.fromString("EUR")
      val to = Currency.fromString("JPY")

      client.getAll
        .map( res => {
          val given = res.find(_._1 == Rate.Pair(from, to)).get._1
          assert(given.from == from)
          assert(given.to == to)
        })

    }
  }

}
