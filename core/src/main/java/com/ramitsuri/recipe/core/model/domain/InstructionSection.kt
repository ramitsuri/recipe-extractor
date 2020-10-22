package com.ramitsuri.recipe.core.model.domain

import com.ramitsuri.recipe.core.model.json.JsonInstructionStep

class InstructionSection(var name: String, jsonInstructions:
List<com.ramitsuri.recipe.core.model.interfaces.Instruction?>) {
    var instructions: MutableList<Instruction>

    init {
        instructions = ArrayList()
        for (step in jsonInstructions) {
            if (step is JsonInstructionStep) {
                instructions.add(Instruction(step.text, step.image))
            }
        }
    }
}