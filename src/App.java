import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";

        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        //String nasaKey = System.getenv("NASA_API_KEY");
        //String url = "https://api.nasa.gov/planetary/apod?api_key=h2IxkhLQNRPvSDk5MmVg5xcH216CSdNIf9YAFaeA&start_date=2022-06-12&end_date=2022-06-14";

        //String url = "http://localhost:8080/linguagens";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        
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
