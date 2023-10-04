package com.example.springwebflux.book

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

//@RestController //webflux 사용시 적용
@RestController("/books") // r2dbc 사용시 적용
class BookController(
//    private val bookService: BookService,
    private val bookRepository: BookRepository,
) {

    /**
     * webflux 사용시 적용할 코드
     */
//    @GetMapping("/books")
//    fun getAll() : Flux<Book>{
//        return bookService.getAll()
//    }
//
//    @GetMapping("/books/{id}")
//    fun get(@PathVariable id: Int) : Mono<Book> {
//        return bookService.get(id)
//    }
//
//    @PostMapping("/books")
//    fun add(@RequestBody request: Map<String, Any>) : Mono<Book> {
//        return bookService.add(request)
//    }
//
//    @DeleteMapping("/books/{id}")
//    fun delete(@PathVariable id: Int) : Mono<Void> {
//        return bookService.delete(id)
//    }


    /**
     * r2dbc 사용
     */
    @GetMapping("{name}")
    fun getByName(@PathVariable name: String) : Mono<Book> {
        return bookRepository.findByName(name)
    }

    @PostMapping
    fun create(@RequestBody map: Map<String, Any>) : Mono<Book> {
        val book = Book(
            name = map["name"].toString(),
            price = map["price"] as Int,
        )
        return bookRepository.save(book)
    }
}