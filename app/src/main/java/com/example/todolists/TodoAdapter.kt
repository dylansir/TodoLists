package com.example.todolists

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolists.R.layout.item_todo
import kotlinx.android.synthetic.main.item_todo.view.*


class TodoAdapter (
    private val todos: MutableList<Todo>
        ) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)




    /* so this will inflate and convert the xml code from item_todo to kotlin code and then we can reference with R. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                /* root = */ parent,
                /* attachToRoot = */ false
            )
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        //go through to dos list and give an instance back
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }



    private fun toggleStrikeThrough(tv_ToDoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tv_ToDoTitle.paintFlags = tv_ToDoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tv_ToDoTitle.paintFlags = tv_ToDoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        //position means the current thing we are working on?
        val curTodo = todos[position]
        holder.itemView.apply {
            tv_ToDoTitle.text = curTodo.title
            cb_Done.isChecked = curTodo.isChecked
            toggleStrikeThrough(tv_ToDoTitle, curTodo.isChecked)
            //checking if it is checked through a lambda func, then adding it in.
            cb_Done.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tv_ToDoTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }


    override fun getItemCount(): Int {
        return todos.size
    }
}