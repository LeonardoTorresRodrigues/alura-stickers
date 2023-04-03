import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoIMDB implements ExtratorDeConteudo {
  
  public List<Conteudo> extraiConteudos(String json) {
    
    var parser = new JsonParser();
    List<Map<String, String>> listaDeAtributos = parser.parse(json);

    return listaDeAtributos.stream()
    .map((Map<String, String> atributos) -> {
      String titulo = atributos.get("title");
      String urlImagem = atributos.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");
      double classificacao = Double.parseDouble(atributos.get("imDbRating"));
      var conteudo = new Conteudo(titulo, urlImagem, classificacao);
      return conteudo;
    })
    .toList();
  }
}
