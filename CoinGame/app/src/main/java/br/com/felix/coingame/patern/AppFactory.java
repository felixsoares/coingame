package br.com.felix.coingame.patern;

import android.content.Context;

import br.com.felix.coingame.R;
import br.com.felix.coingame.dao.ConfiguracaoDAO;
import br.com.felix.coingame.helper.DataBaseService;
import br.com.felix.coingame.model.Configuracao;

public class AppFactory {

    private static Configuracao configuracao;
    private static AppFactory appFactory;
    private static DataBaseService dataBaseService;

    private static ConfiguracaoDAO configuracaoDAO;

    public static void inicializaServicos(Context context){
        dataBaseService = new DataBaseService(context);
        appFactory = new AppFactory();
        configuracaoDAO = new ConfiguracaoDAO();
    }

    public static void inicializaConfiguracao(){
        configuracao = configuracaoDAO.consultaConfiguracao();

        if(configuracao == null){
            configuracao = new Configuracao();
            configuracao.setId(1L);
            configuracao.setTocarMusica(true);
            configuracao.setTocarSom(true);
            configuracao.setQuantidadeJogada(0);
            configuracao.setQuantidadePontosSeguidos(0);
            configuracao.setQuantidadeAcerto(0);
            configuracao.setQuantidadeErro(0);
            configuracao.setQuantidadeCaras(0);
            configuracao.setQuantidadeCoroas(0);
            configuracao.setMoedaSlecionada(R.drawable.coin_game_tras_1);

            configuracaoDAO.incluir(configuracao);
        }

        setConfiguracao(configuracao);
    }

    public static ConfiguracaoDAO getInstanceConfiguracaoDAO(){
        if(configuracaoDAO == null)
            configuracaoDAO = new ConfiguracaoDAO();

        return configuracaoDAO ;
    }

    public static void updateContextDataBase(Context context){
        dataBaseService = new DataBaseService(context.getApplicationContext());
    }

    public static AppFactory getInstance(){
        if(appFactory == null)
            appFactory = new AppFactory();

        return appFactory;
    }

    public static DataBaseService getInstanceDataBaseService(){
        return dataBaseService;
    }

    public static Configuracao getConfiguracao() {
        return configuracao;
    }

    public static void setConfiguracao(Configuracao configuracao) {
        AppFactory.configuracao = configuracao;
    }

    public static void atualizaContadores(int quantidadeErro, int quantidadeAcerto, int quantidadeCara, int quantidadeCoroa, int quantidadeAcertosSeguidos){
        configuracao.setQuantidadeErro(configuracao.getQuantidadeErro() + quantidadeErro);
        configuracao.setQuantidadeAcerto(configuracao.getQuantidadeAcerto() + quantidadeAcerto);
        configuracao.setQuantidadeJogada(configuracao.getQuantidadeAcerto()+configuracao.getQuantidadeErro());
        int pontosSeguidos = configuracao.getQuantidadePontosSeguidos() < quantidadeAcertosSeguidos ? quantidadeAcertosSeguidos : configuracao.getQuantidadePontosSeguidos();
        configuracao.setQuantidadePontosSeguidos(pontosSeguidos);
        configuracao.setQuantidadeCaras(configuracao.getQuantidadeCaras() + quantidadeCara);
        configuracao.setQuantidadeCoroas(configuracao.getQuantidadeCoroas() + quantidadeCoroa);

        configuracaoDAO.atualiza(configuracao);
    }
}
