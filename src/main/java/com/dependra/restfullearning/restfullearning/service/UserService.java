package com.dependra.restfullearning.restfullearning.service;
import com.dependra.restfullearning.restfullearning.dao.CoursesRepository;
import com.dependra.restfullearning.restfullearning.dao.UserRepository;
import com.dependra.restfullearning.restfullearning.model.Courses;
import com.dependra.restfullearning.restfullearning.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CoursesRepository coursesRepository;

    public List<User> getAllUsers(){
        List<User> users=this.userRepository.findAll();
        return users;
    }

    public List<User> getSortedUser(String field){

        List<User> users =this.userRepository.findAll(Sort.by(field));
        return users;
    }

    public Page<User> getPaginatedUser(Pageable pageable){

       Page<User> users =this.userRepository.findAll(pageable);
        return users;
    }

    public User getUserById(int id){
        User user=null;

        try{
            user=this.userRepository.findById(id);
            return user;
        }
        catch (Exception e){
            return  user;
        }
    }

    public User addUser(User user){
        return this.userRepository.save(user);
    }

    public void deleteUser(int id){
        User user=userRepository.findById(id);
        if(user.getCoursesList().size()==0){
            userRepository.deleteById(id);
        }

        else{
//            List<Courses> courses=user.getCoursesList();

//        courses.stream().map(courses1 -> {courses1.setUser(null);return courses1;}).collect(Collectors.toList());
            for(Courses courses:user.getCoursesList()){
                courses.setUser(null);
                //coursesRepository.save(courses);
            }
            this.userRepository.deleteById(id);
        }

    }

    public User updateUser(User user, int id) {
        user.setId(id);
        return userRepository.save(user);

    }
}
