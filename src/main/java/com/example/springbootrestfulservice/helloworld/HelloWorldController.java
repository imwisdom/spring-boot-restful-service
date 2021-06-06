package com.example.springbootrestfulservice.helloworld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    //GET
    // /hello-world (endpoint)
    // @RequestMapping(method=RequestMethod.GET, path = "/hello-world")
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
        //RestController를 사용하면
        //ResponseBody를 하지 않아도 json 형식으로 저장되는 것을 볼 수 있음
    }
    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        //PathVariable 지정 : 가변값을 매개변수로 받음
        //{name}의 name과 String name(매개변수 이름)은 같은 이름을 가져야됨
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

}
