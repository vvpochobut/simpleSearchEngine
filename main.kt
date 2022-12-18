import java.io.File
fun main(args:Array<String>){
    try{
        var work = false
        val date_base = mutableMapOf<Int,MutableList<String>>()
        var a = 0
        if (File(args[1]).exists()) {
            File(args[1]).forEachLine {
                date_base.put( a,(it.split(' ').toMutableList()))
                a++
            }
            work = true
        } else {
            print("No such file")
        }
        while (work){
            println()
            println("""=== Menu ===
1. Find a person
2. Print all people
0. Exit""")
            val choice_menu = readln()
            when(choice_menu){
                "1"-> {
                    println()
                    println("Select a matching strategy: ALL, ANY, NONE")
                    val method_search = readln().toLowerCase()
                    if(method_search== "any"||method_search== "all" ||method_search== "none") {
                        println()
                        people_found(date_base, method_search)
                        println()
                    }
                }
                "2"-> {
                    println()
                    print_date_base(date_base)
                    println()
                }
                "0" -> {
                    println()
                    println("Bye!")
                    work = false
                }
                else -> {
                    println("Incorrect option! Try again.")
                }
            }
        }
    }
    catch(e:java.lang.NumberFormatException){
        println("Not number")
    }
}

fun people_found(date_base:MutableMap<Int,MutableList<String>>,method_search:String){
    val new_array = mutableListOf<String>()
    println("Enter data to search people:")
    val search_people = readln().toString().split(' ')

    for((k,v) in date_base){
        var a = v.toString().toLowerCase()
        var yslovie = when(method_search){
            "any" -> {when(search_people.size){
                1 -> {a.contains(search_people[0].toLowerCase())}
                2 -> {a.contains(search_people[0].toLowerCase()) || a.contains(search_people[1].toLowerCase())}
                3 -> {a.contains(search_people[0].toLowerCase()) || a.contains(search_people[1].toLowerCase()) || a.contains(search_people[2].toLowerCase())}
                else -> false
            }}
            "all" -> when(search_people.size){
                1 -> {a.contains(search_people[0])}
                2 -> {a.contains(search_people[0].toLowerCase()) && a.contains(search_people[1].toLowerCase())}
                3 -> {a.contains(search_people[0].toLowerCase()) && a.contains(search_people[1].toLowerCase()) && a.contains(search_people[2].toLowerCase())}
                else -> false
            }
            "none" -> when(search_people.size){
                1 -> {!a.contains(search_people[0].toLowerCase())}
                2 -> {!a.contains(search_people[0].toLowerCase()) && !a.contains(search_people[1].toLowerCase())}
                3 -> {!a.contains(search_people[0].toLowerCase()) && !a.contains(search_people[1].toLowerCase()) && !a.contains(search_people[2].toLowerCase())}
                else -> false
            }
            else -> when(search_people.size){
                1 -> {!v.contains(search_people[0])}
                2 -> {!v.contains(search_people[0]) &&  !v.contains(search_people[1])}
                3 -> {!v.contains(search_people[0]) && !v.contains(search_people[1]) && !v.contains(search_people[2])}
                else -> false
            }
        }

        if(yslovie){
            new_array.add(v.toString().filterNot { it ==',' }.filterNot { it =='[' }.filterNot { it ==']' })
        }

    }
    if(!new_array.isEmpty()) {

        println(new_array.toSet().joinToString("\n"))
    } else {
        println("No matching people found.")
    }
}

fun print_date_base(date_base: MutableMap<Int, MutableList<String>>){
    for((k,v) in date_base){
        println(v.toString().filterNot { it ==',' }.filterNot { it =='[' }.filterNot { it ==']'})
    }
}
