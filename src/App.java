import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        API api = API.LINGUAGENS;

        String url = api.getUrl();
        
        ExtratorDeConteudo extrator = api.getExtrator();
        
        var http = new ClienteHttp();
        String json = http.buscaDados(url);
        
        List<Conteudo> conteudos = extrator.extraiConteudos(json);
        
        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++) {
            
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = conteudo.titulo();
            double classificacao = conteudo.classificacao();

            String textoFigurinha;
            if (classificacao >= 8.0) {
                textoFigurinha = "TOP " + classificacao  + "/10";
            } else {
                textoFigurinha = "MEH " + classificacao  + "/10";
            }

            geradora.cria(inputStream, nomeArquivo, textoFigurinha);

            //System.out.println("\u001b[1m" + titulo + "\u001b[m");
            //System.out.println("\u2B50 " + nota + "/10");
            System.out.println(nomeArquivo);
            System.out.println();
        }
    }
}
