package com.rp.sec04Operators;

import com.rp.coureutil.Util;
import com.rp.helper.Person;
import reactor.core.publisher.Flux;

import java.util.Locale;
import java.util.function.Function;

public class TransformOperator {
    public static void main(String[] args) {
        getPersonFlux()
                .transform(fluxFluxFunction())
                .subscribe(Util.subscriber());
    }
    public static Flux<Person> getPersonFlux(){
       return Flux.range(1,10)
                .map(integer -> new Person());
    }
    public static Function<Flux<Person>,Flux<Person>> fluxFluxFunction(){
        return (flux)->flux
                        .filter((person)->person.getAge()>10)
                         /*.map(person -> {
                             person.getName().toUpperCase(Locale.ROOT);
                             return person;
                         }) */
                           .doOnNext(person->person.setName(person.getName().toUpperCase(Locale.ROOT)))
                            .doOnDiscard(Person.class,p-> System.out.println("discarding object "+p));
    }
}
