export const filterObject = function (object: any): any {
    return Object.fromEntries(
        Object.entries(object).filter(([_, value]) => value !== null && value !== undefined && value !== "" && value !== "Choose")
    )
}
