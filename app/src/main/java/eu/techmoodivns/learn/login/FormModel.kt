package eu.techmoodivns.learn.login

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.IllegalStateException

class FormModel: ViewModel() {
    private val fieldValues = mutableMapOf<String, MutableLiveData<String>>()
    private val fieldErrors = mutableMapOf<String, MutableLiveData<String>>()

    val consistent = MutableLiveData(false)

    fun addField(name: String, value: String = "") {
        fieldValues[name] = MutableLiveData(value)
        fieldErrors[name] = MutableLiveData(null)
    }

    fun getValue(name: String): MutableLiveData<String> {

        if (!fieldValues.containsKey(name)) {
            throw IllegalStateException("'${name}' not registered!")
        }

        return fieldValues[name]!!
    }

    fun handleChange(field: String, editable: Editable) {

        val input = editable.toString()

        getValue(field).value = input

        refreshConsistency()
    }

    private fun refreshConsistency() {
        consistent.value = noErrors() && allFilled()
    }

    private fun allFilled(): Boolean {
        return fieldValues.values
            .map { v -> v.value }
            .all { v ->  v != null && v.isNotBlank()  }
    }

    private fun noErrors(): Boolean {
        return fieldErrors.values.all { e -> e.value == null }
    }

    fun handleSubmit() {

    }
}
