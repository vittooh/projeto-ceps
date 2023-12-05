class CepDto(
    var cep: String? = null,
    var logradouro: String? = null,
    var complemento: String? = null,
    var bairro: String? = null,
    var localidade: String? = null,
    var uf: String? = null,
    var ibge: String? = null,
    var gia: String? = null,
    var ddd: String? = null,
    var siafi: String? = null
){

    override fun toString(): String {
        return "CepDto(cep=$cep, logradouro=$logradouro, complemento=$complemento, bairro=$bairro, localidade=$localidade, uf=$uf, ibge=$ibge, gia=$gia, ddd=$ddd, siafi=$siafi)"
    }
}
