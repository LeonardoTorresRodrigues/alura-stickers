public class Conteudo {
  
  private final  String titulo;
  private final String urlImagem;
  //private final String nota;

  public Conteudo(String titulo, String urlImagem) {
    this.titulo = titulo;
    this.urlImagem = urlImagem;
    //this.nota = nota;
  }

  public String getTitulo() {
    return titulo;
  }
  
  public String getUrlImagem() {
    return urlImagem;
  }
}
