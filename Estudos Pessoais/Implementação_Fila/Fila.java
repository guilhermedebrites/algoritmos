//Fila = Estrutura básica de fila garante que o primeiro a entrar será o primeiro a sair(FIFO);
public class Fila {
    
    public CelulaTwo primeiro;
    public CelulaTwo ultimo;

    public Fila(){
        primeiro = new CelulaTwo();
        ultimo = primeiro;
    }

    public void inserir(int x){
        ultimo.prox = new CelulaTwo(x);
        ultimo = ultimo.prox;
    }

    public int remover() throws Exception{
        if(primeiro == ultimo){
            throw new Exception("Erro ao remover, Lista vazia!");
        }
        
        CelulaTwo tmp = primeiro;
        primeiro = primeiro.prox;
        int resp = tmp.elemento;
        tmp.prox = null;
        tmp = null;
        return resp;
    }
}
