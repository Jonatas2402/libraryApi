package br.com.joonatas.libraryApi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
//classe de configuração do dataSource
@Configuration
public class DatabaseConfiguration {
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

/*Isso é uma configuração mais detalhada do JPA uma forma opcional, pois o hikari é default do JPA.
    sendo assim ele inicializa com ele. mais sem nenhum pool.
    Mas podemos configurar o pool pelo application.yml
    passando
    hikari:
      maximum-pool-size: 10        # Máximo de conexões simultâneas
      minimum-idle: 2              # Mínimo de conexões ociosas (de pé, prontas pra usar)
      idle-timeout: 30000          # Tempo para uma conexão ociosa ser fechada (30 segundos)
      max-lifetime: 1800000        # Vida útil máxima de uma conexão (30 minutos)
      connection-timeout: 20000    # Tempo máximo para esperar uma conexão antes de dar erro (20 segundos)
      Isso se equivale a essa classe que estamos.
    */
    @Bean
    public DataSource hikari(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);



        config.setMaximumPoolSize(10); //Maxima de conexões liberadas.
        config.setMinimumIdle(1);//Tamanho inicial do pool.
        config.setPoolName("library-db-pool");//Nome do pool.
        config.setMaxLifetime(600000);// 600 mil ms (10 minutos) propriedade em milisegundos.
        config.setConnectionTimeout(100000); //Timeout para conseguir uma conexão.
        config.setConnectionTestQuery("select 1");// query de teste.

        return new HikariDataSource(config);
    }
}

/*O Hikari é um pool de conexões, ele faz com que nossa aplicação quando precise de utilizar o banco de dados
não precise abrir uma conexão utilizar o banco e depois fechar a conexão.
Com o pool ele já deixa uma quantidade de portas aberta para a conexão com o banco, diminuindo o tempo e o recurso da aplicação.
deixando a aplicação mais rápida.
* */