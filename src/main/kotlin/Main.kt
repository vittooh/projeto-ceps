import com.fasterxml.jackson.databind.ObjectMapper
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.file.Files
import kotlin.io.path.Path

fun main(args: Array<String>) {

    var httpClient =  HttpClient.newHttpClient();
    var urlCeps = "https://viacep.com.br/ws/"
    val objectMapper = ObjectMapper()


    val cepsAPesquisar =
        Files.readAllLines(
            Path("C:\\Users\\vitor\\Documents\\digitalbricklayer\\ceps.csv")
        )

    val erros = mutableListOf<String>()
    val processado = mutableListOf<String>()
    processado.add("Cep;Logradouro;complemento;bairro;localidade;uf;")

    cepsAPesquisar.forEach {cepAtual ->
        println("Linha $cepAtual")
      val result =   httpClient.send(HttpRequest.newBuilder(
            URI("$urlCeps$cepAtual/json")
        ).build(), HttpResponse.BodyHandlers.ofString())
        if (!result.body().contains("erro")){
            val cepDto = objectMapper.readValue(result.body(),CepDto::class.java)
            println(cepDto)
            processado.add("${cepDto.cep};" +
                    "${cepDto.logradouro};" +
                    "${cepDto.complemento};" +
                    "${cepDto.bairro};" +
                    "${cepDto.localidade};" +
                    "${cepDto.uf};")
        }else{
            erros.add("Erro ao executar o cep $cepAtual")
        }
    }

    Files.write(Path("processado.csv"),processado)
    Files.write(Path("erro.csv"),erros)

}