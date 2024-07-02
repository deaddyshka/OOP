class Post(
    var id: Int,
    val owner_id: Int,
    val from_id: Int,
    val date: Int,
    val text: String,
    likes: Int,
    val can_pin: Boolean,
    val can_delete: Boolean,
    val can_edit: Boolean,
    val is_pinned: Boolean
) {
    var likes = likes
        set(value) {
            if (value >= 0) {
                field = value
            }
        }
}

fun main() {
    val post = Post(1, 12, 54, 20, "record test test", 5,
        true, false, true, true)

    post.likes=7
    println(post.id)
    println(post.likes)

    println(post.likes)

}