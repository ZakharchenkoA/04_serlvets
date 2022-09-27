package ru.netology.repository;

import ru.netology.model.Post;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {

  private final ConcurrentMap<Long, Post> posts;
  private final AtomicLong idCounter = new AtomicLong();

  public PostRepository() {
    this.posts = new ConcurrentHashMap<>();
  }
  public Collection<Post> all() {
    return posts.values();
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(posts.get(id));
  }

  public Post save(Post savePost) {
    if(savePost.getId() == 0){
      long id = idCounter.incrementAndGet();
      savePost.setId(id);
      posts.put(id, savePost);
    } else if (savePost.getId() != 0) {
      Long currentId = savePost.getId();
      posts.put(currentId, savePost);
    }
    return savePost;
  }

  public void removeById(long id) {
    posts.remove(id);
  }
}
