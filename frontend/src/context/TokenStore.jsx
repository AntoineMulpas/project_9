import{create} from "zustand";

const tokenStore = create((set) => {
    return {
        token: '',
        updateToken: (str) => set(() => ({token: str}))
    }
})


export default tokenStore;