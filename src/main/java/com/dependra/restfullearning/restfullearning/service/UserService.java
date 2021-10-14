package com.dependra.restfullearning.restfullearning.service;
import com.dependra.restfullearning.restfullearning.dao.CoursesRepository;
import com.dependra.restfullearning.restfullearning.dao.UserCourseRepository;
import com.dependra.restfullearning.restfullearning.dao.UserRepository;
import com.dependra.restfullearning.restfullearning.model.User;
import com.dependra.restfullearning.restfullearning.model.UserCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CoursesRepository coursesRepository;

    @Autowired
    UserCourseRepository userCourse;

    public List<User> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users;
    }

    public List<User> getSortedUser(String field) {

        List<User> users = this.userRepository.findAll(Sort.by(field));
        return users;
    }

    public Page<User> getPaginatedUser(Pageable pageable) {

        Page<User> users = this.userRepository.findAll(pageable);
        return users;
    }

    public User getUserById(int id) {
        User user = null;

        try {
            user = this.userRepository.findById(id);
            return user;
        } catch (Exception e) {
            return user;
        }
    }

    public User addUser(User user) {
        return this.userRepository.save(user);
    }

    public void deleteUser(int id) {
        User user = userRepository.findById(id);
        if (user.getUserCourse().size() == 0) {
            userRepository.deleteById(id);
        } else {
            List<UserCourse> userCoursesList = userCourse.getByUserId(user.getId());
            userCourse.deleteAll(userCoursesList);
            userRepository.deleteById(id);
        }
    }

    public User updateUser(User user, int id) {
        user.setId(id);
        return userRepository.save(user);
    }
}
