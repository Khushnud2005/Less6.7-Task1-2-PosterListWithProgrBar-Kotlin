package uz.example.less67_task1_posterlistwithprogrbar_kotlin.model

class Poster{
    var id: Int = 0
    var userId: Int = 0
    lateinit var title: String
    lateinit var body: String

    constructor(userId: Int, title: String, body: String) {
        this.userId = userId
        this.title = title
        this.body = body
    }

    constructor(id: Int, userId: Int, title: String, body: String) {
        this.id = id
        this.userId = userId
        this.title = title
        this.body = body
    }

}