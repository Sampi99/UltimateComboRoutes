import {
    fetchConfig,
    appFetch,
}from "./appFetch";

export const addCombo = (
    characterId,
    secuence,
    difficulty,
    totalDamage,
    demo,
    onSuccess,
    onErrors
) => 
    appFetch(
        "/combos/addCombo", fetchConfig("POST", {characterId, secuence, 
            difficulty, totalDamage, demo}),  
            onSuccess,
            onErrors
        );

export const editCombo = (
    id,
    secuence,
    difficulty,
    totalDamage,
    demo,
    onSuccess,
    onErrors
) =>
    appFetch(
        `/combos/${id}/editCombo`, fetchConfig("PUT", {secuence, difficulty,
            totalDamage, demo}),
            onSuccess,
            onErrors
        );

export const showCharacterCombos = (
    id,
    onSuccess
) =>
    appFetch(
        `/combos/${characterId}`, fetchConfig("GET"),
        onSuccess
    );

export const filterByName = (
    {difficulty, characterId},
    onSuccess
) => 
    appFetch(
        `/combos/${id}/filterByDifficulty?difficulty=${difficulty}`, fetchConfig("GET"),
        onSuccess
    );
    
