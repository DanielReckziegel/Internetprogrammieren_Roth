package de.gsohs.bme5;
import io.javalin.Javalin;

import javax.persistence.Persistence;
import java.util.List;
import java.util.Map;

public class MyServer {
    public static void main(String[] args) {
        Persistence.createEntityManagerFactory("podlike");
        Javalin javalin = Javalin.create().start(8082);
        javalin.config.addStaticFiles("static");
        javalin.get("/hello", ctx -> {
            String name = ctx.queryParam("name", "World");
            ctx.result("Hello "+name+"!");
        });
        javalin.get("/myinfo", ctx -> {
            String out = "";
            for(Map.Entry<String,String> head: ctx.headerMap().entrySet()) {
                out += head.getKey()+" : "+head.getValue()+"\n";
            }
            ctx.result(out);
        });
        javalin.post("/formpost", ctx -> {
            String out = "";
            for(Map.Entry<String, List<String>> form: ctx.formParamMap().entrySet()) {
                out += form.getKey()+" : "+form.getValue()+"\n";
            }
            ctx.result(out);
        });

        javalin.get("/login", ctx -> {
            ctx.render("templates/layout.html");
        });

        javalin.get("/Podcast1", ctx -> {
            ctx.render("templates/Podcast1.html");
        });

        javalin.get("/home", ctx -> {
            ctx.render("templates/Startseite.html");
        });

    }
}
