package tasks.adts

import org.junit.*
import org.junit.Assert.*
import tasks.adts.SchoolModel
import tasks.adts.SchoolModel.BasicSchoolModule
import u03.extensionmethods.Sequences.Sequence.*

class SchoolTest:
  import SchoolModel.BasicSchoolModule.*

  val defaultSchool = emptySchool

  @Test def testEmptySchoolByDefault(): Unit =
    assertEquals(Nil(), defaultSchool)

  @Test def testEmptyTeachersByDefault(): Unit =
    assertEquals(Nil(), defaultSchool.teachers)

  @Test def testEmptyCoursesByDefault(): Unit =
    assertEquals(Nil(), defaultSchool.courses)

  @Test def testSetTeacherToCourse(): Unit =
    val teacher: Teacher = BasicSchoolModule.teacher("Viroli")
    val course: Course = BasicSchoolModule.course("PPS")
    assertEquals(Cons((teacher, course), Nil()), defaultSchool.setTeacherToCourse(teacher, course))

  @Test def testCoursesOfATeacher(): Unit =
    val teacher: Teacher = BasicSchoolModule.teacher("Viroli")
    val course: Course = BasicSchoolModule.course("PPS")
    val school = defaultSchool.setTeacherToCourse(teacher, course)
    assertEquals(Cons(course, Nil()), school.coursesOfATeacher(teacher))

  @Test def testSchoolHasTeacher(): Unit =
    val teacher: Teacher = BasicSchoolModule.teacher("Viroli")
    val course: Course = BasicSchoolModule.course("PPS")
    val school = defaultSchool.setTeacherToCourse(teacher, course)
    assertEquals(true, school.hasTeacher(teacher))

  @Test def testSchoolHasCourse(): Unit =
    val teacher: Teacher = BasicSchoolModule.teacher("Viroli")
    val course: Course = BasicSchoolModule.course("PPS")
    val school = defaultSchool.setTeacherToCourse(teacher, course)
    assertEquals(true, school.hasCourse(course))
